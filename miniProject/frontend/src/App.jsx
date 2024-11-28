import React from 'react';
import { BrowserRouter as Router, Routes, Route, Navigate } from 'react-router-dom';
import Login from './components/Login';
import Home from './components/Home';
import SalaryDetails from './pages/SalaryDetails';
import Sidebar from './components/Sidebar';
import { isAuthenticated } from './hooks/useAuth';

const App = () => {
  return (
    <Router>
      <div className="flex">
        {/* Sidebar will only show if the user is authenticated */}
        {isAuthenticated && <Sidebar />} {/* Conditionally render Sidebar */}
        {/* Main Content */}
        <div className="flex-1 p-8">
          <Routes>
            {/* Redirect to /home if user is logged in and trying to access / */}
            <Route path="/" element={isAuthenticated ? <Navigate to="/home" /> : <Login />} />
            {/* Other routes */}
            <Route path="/home" element={isAuthenticated ? <Home /> : <Navigate to="/" />} />
            <Route
              path="/salary-details"
              element={isAuthenticated ? <SalaryDetails /> : <Navigate to="/" />}
            />
          </Routes>
        </div>
      </div>
    </Router>
  );
};
export default App;
