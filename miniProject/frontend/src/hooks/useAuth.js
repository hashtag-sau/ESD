// src/hooks/useAuth.js
import { useState, useEffect } from 'react';
import { getAccessToken, removeTokens } from '../services/storage';

const useAuth = () => {
  const [isAuthenticated, setIsAuthenticated] = useState(!!getAccessToken());

  useEffect(() => {
    setIsAuthenticated(!!getAccessToken());
  }, []);

  const logout = () => {
    removeTokens();
    setIsAuthenticated(false);
    window.location.href = '/';
  };

  return { isAuthenticated, setIsAuthenticated, logout };
};

export default useAuth;
