import { useEffect } from 'react';
import { useNavigate } from 'react-router-dom';

const Logout = ({ onLogout }) => {
  const navigate = useNavigate();

  useEffect(() => {
    // Perform logout
    onLogout();
    // Redirect user to login page after logout
    navigate('/');
  }, [onLogout, navigate]);

  return null; // Nothing needs to be rendered
};

export default Logout;
