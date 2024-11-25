import React from 'react';
import { Link, useNavigate } from 'react-router-dom';

const Navbar = () => {
  const navigate = useNavigate();

  const handleLogout = () => {
    localStorage.removeItem('token'); // Remove token from storage
    navigate('/'); // Redirect to login page
  };

  return (
    <nav className="bg-blue-600 text-white px-4 py-3 flex justify-between items-center">
      <div className="flex gap-4">
        <Link to="/home" className="hover:underline">
          Home
        </Link>
        <Link to="/dashboard" className="hover:underline">
          Dashboard
        </Link>
        <Link to="/profile" className="hover:underline">
          Profile
        </Link>
      </div>
      <button onClick={handleLogout} className="bg-red-500 px-3 py-1 rounded hover:bg-red-600">
        Logout
      </button>
    </nav>
  );
};

export default Navbar;
