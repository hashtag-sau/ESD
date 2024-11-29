import { useState } from 'react';
import { Link, useNavigate } from 'react-router-dom';
import useAuth from '../hooks/useAuth';
import SidebarUI from './Presentation/SidebarUI';

export default function Sidebar() {
  const [active, setActive] = useState('Home');
  const navigate = useNavigate();
  const { isAuthenticated, setIsAuthenticated, logout } = useAuth();

  // Menu items array
  const menuItems = [
    { name: 'Home', icon: '🏠', path: '/home' },
    { name: 'Salary Details', icon: '💼', path: '/salary-details' },
    { name: 'Profile Page', icon: '👤', path: '/profile' }
  ];

  const handleLogout = () => {
    logout();
  };

  console.log('Sidebar rendered');

  return (
    <SidebarUI
      menuItems={menuItems}
      active={active}
      setActive={setActive}
      handleLogout={handleLogout}
    />
  );
}
