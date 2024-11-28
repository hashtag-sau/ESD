// hooks/useUserDetails.js

import { useState, useEffect } from 'react';
import { fetchSalaryData, fetchEmployeeData } from '../utils/httputils';
import { User } from '../model/Users';

export const useUserDetails = () => {
  const [userDetails, setUserDetails] = useState(null);
  const [salaryData, setSalaryData] = useState([]);

  useEffect(() => {
    const fetchData = async () => {
      try {
        const data = await fetchSalaryData();
        setSalaryData(data);

        const getuser = await fetchEmployeeData();
        console.log(getuser);

        const user = new User({
          name: `${getuser.first_name}`,
          designation: getuser.title,
          profilePic: getuser.photograph_path
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
