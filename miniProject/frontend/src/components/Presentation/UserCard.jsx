// components/Presentation/UserCard.jsx

const UserCard = ({ user }) => {
  return (
    <header className="flex justify-between items-center mb-6">
      <div>
        <h1 className="text-2xl font-bold">Welcome {user.name}!</h1>
        <p className="text-gray-600">{user.designation}</p>
      </div>
      <img
        src={`http://localhost:8080/api/v1/employee/file/profile/${user.profilePic}`}
        alt="Profile"
        className="rounded-full w-32 h-32"
      />
    </header>
  );
};

export default UserCard;
