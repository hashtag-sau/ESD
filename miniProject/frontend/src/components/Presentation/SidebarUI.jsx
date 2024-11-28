import { Link } from 'react-router-dom';

const Sidebar = ({ menuItems, active, setActive, handleLogout }) => {
  return (
    <div className="bg-gray-800 text-white h-screen w-64 flex flex-col p-4">
      <div className="text-xl font-bold mb-6">IIITB -ERP</div>
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
};

export default Sidebar;
