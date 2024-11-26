import React from 'react';
import { BrowserRouter as Router, Routes, Route } from 'react-router-dom';
import Login from './components/Login';
import Home from './components/Home';
import SalaryDashboard from './components/SalaryDashboard';
import Profile from './components/Profile';
import Navbar from './components/Navbar';
import { useNavigate } from 'react-router-dom';

const App = () => {
  // const isLoggedIn = !!localStorage.getItem('token');
  //const Navigate = useNavigate();

  // if (!isLoggedIn) {
  //   console.log('not logged in');
  //   return (
  //     <Router>
  //       <Navigate to="/" />
  //     </Router>
  //   );
  // }
  return (
    <Router>
      {/* {isLoggedIn && <Navbar />} */}
      <Routes>
        <Route path="/" element={<Login />} />
        <Route path="/home" element={<Home />} />
        <Route path="/dashboard" element={<SalaryDashboard />} />
        <Route path="/profile" element={<Profile />} />
      </Routes>
    </Router>
  );
};
export default App;
