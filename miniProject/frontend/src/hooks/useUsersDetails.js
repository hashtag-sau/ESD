// hooks/useUserDetails.js

import { useState, useEffect } from 'react';
import { fetchSalaryData } from '../utils/httputils';
import { User } from '../model/Users';

export const useUserDetails = () => {
  const [userDetails, setUserDetails] = useState(null);
  const [salaryData, setSalaryData] = useState([]);

  useEffect(() => {
    const fetchData = async () => {
      try {
        const data = await fetchSalaryData();
        setSalaryData(data);

        // Example User Data
        const user = new User({
          name: 'Saurabh',
          designation: 'Software Engineer',
          profilePic: 'https://via.placeholder.com/41'
        });
        setUserDetails(user);
      } catch (error) {
        alert(error.message);
      }
    };

    fetchData();
  }, []);

  return { userDetails, salaryData };
};
