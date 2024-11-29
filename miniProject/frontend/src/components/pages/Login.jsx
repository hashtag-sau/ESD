import React, { useState, useEffect } from 'react';
import { useNavigate } from 'react-router-dom';
import { saveTokens } from '../../services/storage';
import useAuth from '../../hooks/useAuth';
import { login as doLogin } from '../../services/authService';
import LoginUI from '../Presentation/LoginUI';
const Login = ({ onLoginSuccess }) => {
  const [email, setEmail] = useState('');
  const [password, setPassword] = useState('');

  const navigate = useNavigate();

  const handleLogin = async (e) => {
    e.preventDefault();
    try {
      if (!email || !password) {
        alert('Please fill in both email and password.');
        return;
      }
      const emailRegex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
      if (!emailRegex.test(email)) {
        alert('Please enter a valid email address.');
        return;
      }
      const { access_token, refresh_token } = await doLogin(email, password);
      saveTokens(access_token, refresh_token); //saving tokens that we got from the server
      console.log(access_token, refresh_token);
      onLoginSuccess();
      navigate('/home');
    } catch (error) {
      alert('Login failed! Please check your credentials.');
    }
  };

  return (
    <LoginUI
      email={email}
      password={password}
      setEmail={setEmail}
      setPassword={setPassword}
      handleLogin={handleLogin}
    />
  );
};

export default Login;
