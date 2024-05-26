import React, { useState } from 'react';
import './App.css'; // Import your CSS file
import EmployeeComponent from './Components/EmployeeComponent';
import EmployeeList from './Components/EmployeeList';
import FooterComponent from './Components/FooterComponent';
import HeaderComponent from './Components/HeaderComponent';
import { BrowserRouter as Router, Routes, Route } from 'react-router-dom';
import NotFoundPage from './Components/NotFoundPage';

function App() {
  const [invert, setInvert] = useState(false);

  const toggleInvert = () => {
    const root = document.documentElement;
    root.style.filter = invert ? 'invert(0)' : 'invert(0.9)';
    setInvert(!invert);
  };

  return (
    <>
      <Router>
        {/* header and footer component will always remain independent of routes and be shown on the screen */}
        <HeaderComponent />
        <button className='btn btn-primary m-2' onClick={toggleInvert}>
          {invert ? 'Light Mode ' : 'Dark Mode 🌙'}
        </button>
        <Routes>
          <Route path="/" element={<EmployeeList />} />
          <Route path="/add-employee" element={<EmployeeComponent />} />
          <Route path="/edit-employee/:id" element={<EmployeeComponent />} />
          <Route path="*" element={<NotFoundPage />} />
        </Routes>
        <FooterComponent />
      </Router>
    </>
  );
}

export default App;
