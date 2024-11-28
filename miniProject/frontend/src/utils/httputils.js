import axiosClient from "./axiosClient";


export const fetchSalaryData = async (token) => {
  try {
    const response = await axios.get(/salary/all);
    return response.data;
  } catch (error) {
    throw new Error('Failed to fetch salary data.');
  }
};

export const fetchEmployeeData = async (token) => {
  try {
    const response = await axios.get(/employee/detail);
    return response.data;
  } catch (error) {
    throw new Error('Failed to fetch employee data.');
  }
}
