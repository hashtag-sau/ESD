import React, { useState } from 'react';
import { useNavigate } from 'react-router-dom';
import axios from 'axios';

const Login = () => {
  const [email, setEmail] = useState('');
  const [password, setPassword] = useState('');
  const navigate = useNavigate();

  const handleLogin = async (e) => {
    e.preventDefault();
    try {
      const response = await axios.post('http://localhost:8080/api/v1/employee/login', {
        email,
        password
      });
      console.log(response.data);
      localStorage.setItem('token', response.data.access_token);
      navigate('/dashboard');
    } catch (error) {
      alert('Login failed!');
    }
  };

  return (
    <form className="flex flex-col gap-4 p-4">
      <h2 className="text-xl font-bold">Login</h2>
      <input
        type="email"
        placeholder="Email"
        value={email}
        onChange={(e) => setEmail(e.target.value)}
        className="border p-2"
      />
      <input
        type="password"
        placeholder="Password"
        value={password}
        onChange={(e) => setPassword(e.target.value)}
        className="border p-2"
      />
      <button onClick={handleLogin} className="bg-blue-500 text-white p-2">
        Login
      </button>
    </form>
  );
};

export default Login;
