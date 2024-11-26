import React from 'react';
import { Link, useNavigate } from 'react-router-dom';
import useAuth from '../hooks/useAuth'; // Import useAuth hook

const Navbar = () => {
  const { logout } = useAuth(); // Use logout from the hook
  const navigate = useNavigate();

  const handleLogout = () => {
    logout(); // Call the logout function from useAuth hook
    navigate('/'); // Redirect to the login page
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
