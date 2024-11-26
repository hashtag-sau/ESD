// src/hooks/useSalaryData.js
import { useState, useEffect } from 'react';
import axiosClient from '../utils/axiosClient';

const useSalaryData = () => {
  const [salaryData, setSalaryData] = useState([]);
  const [loading, setLoading] = useState(false);
  const [error, setError] = useState(null);

  // Function to fetch salary data from API
  const fetchSalaryData = () => {
    axiosClient
      .get('/salary/all')
      .then((response) => {
        setSalaryData(response.data);
      })
      .catch((error) => {
        setError('Failed to fetch salary data.');
      })
      .finally(() => {
        setLoading(false);
      });
  };

  // Fetch salary data when the component mounts
  useEffect(() => {
    fetchSalaryData();
  }, []);

  return { salaryData, loading, error };
};

export default useSalaryData;
