import React from 'react';
import { BrowserRouter as Router, Routes, Route, Navigate } from 'react-router-dom';
import Login from './components/Login';
import Home from './components/Home';
import SalaryDetails from './components/SalaryDetails';
import Sidebar from './components/Sidebar';
import useAuth from './hooks/useAuth';

const App = () => {
  const { isAuthenticated, handleLoginSuccess } = useAuth();
  console.log(isAuthenticated);
  return (
    <Router>
      <div className="flex">
        {/* Sidebar will only show if the user is authenticated */}
        {isAuthenticated && <Sidebar />} {/* Conditionally render Sidebar */}
        {/* Main Content */}
        <div className="flex-1 p-8">
          <Routes>
            {/* Other routes */}
            <Route path="/home" element={isAuthenticated ? <Home /> : <Navigate to="/" />} />
            <Route
              path="/"
              element={
                isAuthenticated ? (
                  <Navigate to="/home" />
                ) : (
                  <Login onLoginSuccess={handleLoginSuccess} />
                )
              }
            />
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
