import React from 'react';
import { Link } from 'react-router-dom';
import useAuth from '../hooks/useAuth';
import useProfile from '../hooks/useProfile';

const Home = () => {
  const { isAuthenticated } = useAuth();
  const { profile, loading, error } = useProfile();

  if (!isAuthenticated) {
    return (
      <div>
        <h1>Hello Employee</h1>
        <p>Please login to view your salary</p>
        <Link to="/">Click this to Login</Link>
      </div>
    );
  } else {
    return (
      <div>
        <h1>Hello {loading ? 'Employee' : profile?.first_name}</h1>
        <Link to="/dashboard">View Salary</Link>
      </div>
    );
  }
};

export default Home;
