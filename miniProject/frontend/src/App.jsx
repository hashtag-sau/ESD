import React from 'react';
import { BrowserRouter as Router, Routes, Route, Navigate } from 'react-router-dom';
import Login from './components/pages/Login';
import Home from './components/pages/Home';
import SalaryDetails from './components/pages/SalaryDetails';
import Sidebar from './components/Sidebar';
import Profile from './components/pages/Profile';
import useAuth from './hooks/useAuth';

const App = () => {
  const { isAuthenticated, handleLoginSuccess } = useAuth();
  console.log(isAuthenticated);
  return (
    <Router>
      <div className="flex">
        {/* Sidebar will only show if the user is authenticated */}
        {isAuthenticated && <Sidebar />}

        <div className="flex-1 p-8">
          <Routes>
            {/*routes will be done on basis of whether logged in*/}
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

            <Route path="/profile" element={isAuthenticated ? <Profile /> : <Navigate to="/" />} />
          </Routes>
        </div>
      </div>
    </Router>
  );
};
export default App;
