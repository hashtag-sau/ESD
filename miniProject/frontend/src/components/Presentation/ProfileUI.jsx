const ProfileUI = ({
  employee,
  updatedEmployee,
  isEditing,
  setIsEditing,
  handleChange,
  handleSubmit
}) => {
  return (
    <div className="p-6 bg-gray-100 min-h-screen font-sans">
      <div className="bg-white shadow-md p-6 rounded-lg space-y-6">
        <h2 className="text-2xl font-semibold text-gray-800">Employee Profile</h2>
        {!isEditing ? (
          <div className="space-y-4 text-gray-700">
            <div className="flex justify-between items-center">
              <strong className="text-lg">First Name:</strong>
              <span className="font-medium text-xl">{employee.first_name}</span>
            </div>
            <div className="flex justify-between items-center">
              <strong className="text-lg">Last Name:</strong>
              <span className="font-medium text-xl">{employee.last_name}</span>
            </div>
            <div className="flex justify-between items-center">
              <strong className="text-lg">Email:</strong>
              <span className="font-medium text-xl">{employee.email}</span>
            </div>
            <div className="flex justify-between items-center">
              <strong className="text-lg">Title:</strong>
              <span className="font-medium text-xl">{employee.title}</span>
            </div>
            <div className="flex justify-between items-center">
              <strong className="text-lg">Department:</strong>
              <span className="font-medium text-xl">{employee.department}</span>
            </div>
            <button
              className="mt-6 bg-blue-600 text-white px-6 py-3 rounded-md shadow-md hover:bg-blue-700 transition duration-300"
              onClick={() => setIsEditing(true)}
            >
              Edit Profile
            </button>
          </div>
        ) : (
          <form onSubmit={handleSubmit} className="space-y-4 text-gray-700">
            <div className="flex flex-col">
              <label htmlFor="first_name" className="text-lg">
                First Name:
              </label>
              <input
                id="first_name"
                type="text"
                name="first_name"
                value={updatedEmployee.first_name}
                onChange={handleChange}
                className="px-4 py-2 rounded-md bg-gray-200 text-gray-800"
              />
            </div>
            <div className="flex flex-col">
              <label htmlFor="last_name" className="text-lg">
                Last Name:
              </label>
              <input
                id="last_name"
                type="text"
                name="last_name"
                value={updatedEmployee.last_name}
                onChange={handleChange}
                className="px-4 py-2 rounded-md bg-gray-200 text-gray-800"
              />
            </div>
            <div className="flex flex-col">
              <label htmlFor="email" className="text-lg">
                Email:
              </label>
              <input
                id="email"
                type="email"
                name="email"
                value={updatedEmployee.email}
                onChange={handleChange}
                className="px-4 py-2 rounded-md bg-gray-200 text-gray-800"
                disabled
              />
            </div>
            <div className="flex flex-col">
              <label htmlFor="title" className="text-lg">
                Title:
              </label>
              <input
                id="title"
                type="text"
                name="title"
                value={updatedEmployee.title}
                onChange={handleChange}
                className="px-4 py-2 rounded-md bg-gray-200 text-gray-800"
              />
            </div>
            <div className="flex flex-col">
              <label htmlFor="department" className="text-lg">
                Department:
              </label>
              <input
                id="department"
                type="number"
                name="department"
                value={updatedEmployee.department}
                onChange={handleChange}
                className="px-4 py-2 rounded-md bg-gray-200 text-gray-800"
              />
            </div>
            <button
              type="submit"
              className="mt-6 bg-blue-600 text-white px-6 py-3 rounded-md shadow-md hover:bg-blue-700 transition duration-300"
            >
              Save Changes
            </button>
          </form>
        )}
      </div>
    </div>
  );
};

export default ProfileUI;
