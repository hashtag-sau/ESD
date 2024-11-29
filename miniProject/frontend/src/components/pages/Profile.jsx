import React, { useEffect, useState } from 'react';
import { useNavigate } from 'react-router-dom';
import { fetchEmployeeData, updateEmployeeData } from '../../utils/httputils';
import ProfileUI from '../Presentation/ProfileUI';

const Profile = () => {
  const [employee, setEmployee] = useState(null);
  const [isEditing, setIsEditing] = useState(false);
  const [updatedEmployee, setUpdatedEmployee] = useState({
    first_name: '',
    last_name: '',
    email: '',
    title: '',
    department: ''
  });

  // Fetch employee data on component mount
  useEffect(() => {
    const getEmployeeData = async () => {
      try {
        const data = await fetchEmployeeData();
        setEmployee(data);
        setUpdatedEmployee(data); // Pre-populate form with current data
      } catch (error) {
        console.error('Error fetching employee data:', error);
      }
    };
    getEmployeeData();
  }, []);

  // Handle form field changes
  const handleChange = (e) => {
    const { name, value } = e.target;
    setUpdatedEmployee((prev) => ({
      ...prev,
      [name]: value
    }));
  };

  // Handle form submission (update employee data)
  const handleSubmit = async (e) => {
    e.preventDefault();
    try {
      const response = await updateEmployeeData(updatedEmployee);
      if (response.status === 200) {
        setEmployee(updatedEmployee); // Update employee state with the new data
        setIsEditing(false);
      }
    } catch (error) {
      console.error('Error updating employee data:', error);
    }
  };

  // Display loading state if data is not loaded
  if (!employee) {
    return <div>Loading...</div>;
  }

  return (
    <ProfileUI
      employee={employee}
      updatedEmployee={updatedEmployee}
      isEditing={isEditing}
      setIsEditing={setIsEditing}
      handleChange={handleChange}
      handleSubmit={handleSubmit}
    />
  );
};

export default Profile;
