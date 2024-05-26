import React, { useState, useEffect } from 'react'
import { listEmployees, deleteEmployee } from '../services/EmployeeService';
import { useNavigate } from 'react-router-dom';

function EmployeeList() {

    const [employees, setEmployees] = useState([]);

    // to fetch an api we need to use useEffet hook 
    useEffect(() => {
        getListEmployees();
    }, []);

    //create getListEmployees component 
    function getListEmployees() {
        listEmployees().then((response) => {
            setEmployees(response.data);
            console.log(response.data);
        }).catch(error => {
            console.error(error);
        })
    }

    const navigate = useNavigate();

    const addNewEmployee = () => {
        navigate('/add-employee');
    };

    const handleUpdate = (id) => () => {
        navigate(`/edit-employee/${id}`);
    }
    //handle delete
    const handleDelete = (id) => () => {
        deleteEmployee(id).then((response) => {
            console.log(response.data);
            getListEmployees();
        }).catch(error => {
            console.error(error);
        })
    }

    return (
        <>

            <a className='m-4' onClick={addNewEmployee}>Add New Employee </a>
            <div className="container" style={{ height: '70vh', width: "80vw", overflowY: "scroll", overFlowX: "auto" }} >
                <h2 className='text-center mb-4'>List of Employees</h2>
                <table className="table table-striped table-bordered">
                    <thead>
                        <tr className="text-center">
                            <th>Employee ID</th>
                            <th>Employee First Name</th>
                            <th>Employee Last Name</th>
                            <th>Employee Email Id</th>
                            <th>Actions</th>
                        </tr>
                    </thead>
                    <tbody className="text-center">
                        {
                            employees.map(employee =>
                                <tr key={employee.id}>
                                    <td>{employee.id}</td>
                                    <td>{employee.firstName}</td>
                                    <td>{employee.lastName}</td>
                                    <td>{employee.email}</td>
                                    <td className="flex justify-center ">
                                        <button onClick={handleUpdate(employee.id)} className="btn btn-primary m-2">Update</button>
                                        <button onClick={handleDelete(employee.id)} className="btn btn-danger m-2">Delete</button>
                                    </td>
                                </tr>
                            )
                        }
                        <tr>
                        </tr>
                    </tbody>
                </table>
            </div>
        </>
    )
}

export default EmployeeList