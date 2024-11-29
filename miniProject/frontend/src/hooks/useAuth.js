// src/hooks/useAuth.js
import { useState, useEffect } from 'react';
import { getAccessToken, removeTokens } from '../services/storage';

const useAuth = () => {
  const [isAuthenticated, setIsAuthenticated] = useState(false);

  useEffect(() => {
    const token = getAccessToken();
    if (token) {
      setIsAuthenticated(true);
    }
  }, []);

  const logout = () => {
    removeTokens();
    setIsAuthenticated(false);
    window.location.href = '/';
  };

  const handleLoginSuccess = () => {
    setIsAuthenticated(true);
  };

  return { isAuthenticated, setIsAuthenticated, logout, handleLoginSuccess };
};

export default useAuth;
