// src/components/common/PrivateRoute.jsx
import React from 'react';
import { Navigate } from 'react-router-dom';
import { getAccessToken } from '../../services/storage';

const PrivateRoute = ({ children }) => {
  const isAuthenticated = !!getAccessToken();
  return isAuthenticated ? children : <Navigate to="/login" />;
};

export default PrivateRoute;
