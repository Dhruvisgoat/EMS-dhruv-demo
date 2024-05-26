import React from 'react';
import { Link } from 'react-router-dom';


const NotFoundPage = () => {
  return (
    <div className="flex justify-center items-center min-h-screen text-center bg-gray-100">
      <h1 className="text-9xl text-blue-600 mb-8">404</h1>
      <h2 className="text-4xl text-gray-700 mb-4">Oops! Page not found</h2>
      <p className="text-lg text-gray-500 mb-8">
        The page you are looking for might have been removed or is temporarily unavailable.
      </p>
      <Link to="/">
        <button className="btn btn-primary">
          Back to Home
        </button>
      </Link>
    </div>
  );
};

export default NotFoundPage;
