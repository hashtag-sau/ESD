import { useState, useEffect } from 'react';
import { fetchSalaryData } from '../utils/httputils';
import Salary from '../model/Salary';

const useSalaryDetails = () => {
  const [salaryData, setSalaryData] = useState([]);
  const [financialYears, setFinancialYears] = useState([]);
  const [currentYear, setCurrentYear] = useState('');
  const [selectedMonth, setSelectedMonth] = useState(null);

  useEffect(() => {
    const getSalaryData = async () => {
      try {
        const data = await fetchSalaryData();

        // Map data to Salary instances
        const salaries = data.map((item) => new Salary(item));

        // Extract unique financial years
        const years = [...new Set(salaries.map((salary) => salary.getFinancialYear()))];

        setSalaryData(salaries);
        setFinancialYears(years);

        // Set current financial year and default month
        const currentFY = years[0]; // Assuming latest year first
        setCurrentYear(currentFY);

        const defaultMonth = salaries.find((salary) => salary.getFinancialYear() === currentFY);
        setSelectedMonth(defaultMonth);
      } catch (error) {
        alert(error.message);
      }
    };

    getSalaryData();
  }, []);

  return {
    salaryData,
    financialYears,
    currentYear,
    selectedMonth,
    setCurrentYear,
    setSelectedMonth
  };
};

export default useSalaryDetails;
