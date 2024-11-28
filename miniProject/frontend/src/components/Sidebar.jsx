import { useState } from 'react';
import { Link, useNavigate } from 'react-router-dom';

export default function Sidebar() {
  const [active, setActive] = useState('Home');
  const navigate = useNavigate();

  // Menu items array
  const menuItems = [
    { name: 'Home', icon: '🏠', path: '/home' },
    { name: 'Salary Details', icon: '💼', path: '/salary-details' }
  ];

  const handleLogout = () => {
    navigate('/logout'); // Navigate to the logout route
  };

  // Check if the user is authenticated
  const isAuthenticated = localStorage.getItem('token');

  if (!isAuthenticated) {
    // If not authenticated, do not show the sidebar
    return null;
  }

  return (
    <div className="bg-gray-800 text-white h-screen w-64 flex flex-col p-4">
      <div className="text-xl font-bold mb-6">Coditas Solutions LLP</div>
      <nav className="space-y-4">
        {menuItems.map((item) => (
          <Link to={item.path} key={item.name}>
            <div
              className={`flex items-center space-x-3 p-3 rounded-md cursor-pointer ${
                active === item.name ? 'bg-blue-600 text-white' : 'hover:bg-gray-700'
              }`}
              onClick={() => setActive(item.name)}
            >
              <span>{item.icon}</span>
              <span>{item.name}</span>
            </div>
          </Link>
        ))}
      </nav>
      <button
        className="mt-6 bg-red-600 hover:bg-red-700 text-white py-2 px-4 rounded-md"
        onClick={handleLogout}
      >
        Logout
      </button>
    </div>
  );
}
