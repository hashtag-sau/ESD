//Http request functions
//this function is used to do api call to get data
import axiosClient from './axiosClient';

export const fetchSalaryData = async (token) => {
  try {
    const response = await axiosClient.get('/salary/all');
    return response.data;
  } catch (error) {
    throw new Error('Failed to fetch salary data.');
  }
};

export const fetchEmployeeData = async (token) => {
  try {
    const response = await axiosClient.get('/detail');
    return response.data;
  } catch (error) {
    throw new Error('Failed to fetch employee data.');
  }
};

export const updateEmployeeData = async (data) => {
  try {
    const response = await axiosClient.put('/detail', data);
    return response;
  } catch (error) {
    throw new Error('Failed to update employee data.');
  }
};
