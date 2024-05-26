import React, { useState, useEffect } from 'react';
import { useNavigate, useParams } from 'react-router-dom';
import { createEmployee, getEmployee, updateEmployee, deleteEmployee } from '../services/EmployeeService';

function EmployeeComponent() {
    const [firstName, setFirstName] = useState('');
    const [lastName, setLastName] = useState('');
    const [email, setEmail] = useState('');
    const [errors, setErrors] = useState({ firstName: '', lastName: '', email: '' });

    const navigate = useNavigate();

    const handleButton = () => {
        navigate('/');
    };

    const { id } = useParams();

    useEffect(() => {
        if (id) {
            getEmployee(id).then((response) => {
                const employee = response.data;
                setFirstName(employee.firstName);
                setLastName(employee.lastName);
                setEmail(employee.email);
            })
                .catch(error => {
                    console.error(error)
                })
        }
    }, [id]);



    const validateForm = () => {
        const errors = {};
        if (!firstName.trim()) {
            errors.firstName = 'First Name is required';
        }
        if (!lastName.trim()) {
            errors.lastName = 'Last Name is required';
        }
        if (!email.trim()) {
            errors.email = 'Email is required';
        } else if (!/\S+@\S+\.\S+/.test(email)) {
            errors.email = 'Email is invalid';
        }
        return errors;
    };

    const handleSaveOrUpdate = (e) => {
        e.preventDefault(); // Prevent the default form submission behavior

        const errors = validateForm();
        if (Object.keys(errors).length === 0) {
            const employee = { firstName, lastName, email };

            if (id) {
                updateEmployee(employee, id).then((response) => {
                    console.log(response.data);
                    navigate('/');
                }
                ).catch(error => {
                    console.error(error);
                });
            }
            else {
                createEmployee(employee).then((response) => {
                    console.log(response.data);
                    navigate('/');
                }).catch(error => {
                    console.error(error);
                });
            }
        } else {
            setErrors(errors);
        }
    };

    const handleDelete = () => {
        if (id) {
            deleteEmployee(id).then((response) => {
                console.log(response.data);
                navigate('/');
            }).catch(error => {
                console.error(error);
            });
        }
    };

    function pageTitle() {
        if (id) {
            return <h3 className='text-center mt-4'>Edit Employee</h3>
        } else {
            return <h3 className='text-center mt-4'>Add Employee</h3>
        }
    }

    return (
        <>
         <a className='m-4' onClick={handleButton}>Go to Employee List </a>
            <div className='container' style={{height:'70vh' ,width:"80vw",overflowY:"scroll",overFlowX:"auto"}}>
                <div className='mt-3'>
                    <div className='card col-md-6 offset-md-3 offset-md-3'>
                        {pageTitle()}
                        <div className='card-body'>
                            <form>
                                <div className='form-group m-2'>
                                    <label>First Name:</label>
                                    <input
                                        placeholder='First Name'
                                        name='firstName'
                                        className={`form-control ${errors.firstName ? 'is-invalid' : ''}`}
                                        value={firstName}
                                        onChange={(e) => setFirstName(e.target.value)}
                                    />
                                    {errors.firstName && (
                                        <div className='invalid-feedback'>{errors.firstName}</div>
                                    )}
                                </div>
                                <div className='form-group m-2'>
                                    <label>Last Name:</label>
                                    <input
                                        placeholder='Last Name'
                                        name='lastName'
                                        className={`form-control ${errors.lastName ? 'is-invalid' : ''}`}
                                        value={lastName}
                                        onChange={(e) => setLastName(e.target.value)}
                                    />
                                    {errors.lastName && (
                                        <div className='invalid-feedback'>{errors.lastName}</div>
                                    )}
                                </div>
                                <div className='form-group m-2'>
                                    <label>Email:</label>
                                    <input
                                        placeholder='Email'
                                        name='email'
                                        className={`form-control ${errors.email ? 'is-invalid' : ''}`}
                                        value={email}
                                        onChange={(e) => setEmail(e.target.value)}
                                    />
                                    {errors.email && (
                                        <div className='invalid-feedback'>{errors.email}</div>
                                    )}
                                </div>
                                <button
                                    className='btn btn-success m-2'
                                    onClick={handleSaveOrUpdate}
                                >Save</button>
                                <button
                                    className='btn btn-danger m-2'
                                    onClick={handleDelete}
                                >Delete</button>
                                
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </>
    );
}

export default EmployeeComponent;
