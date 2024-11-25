import React, { useState, useEffect } from 'react';
import axios from 'axios';
import jsPDF from 'jspdf';

const SalaryDashboard = () => {
  const [salaryData, setSalaryData] = useState([]);

  useEffect(() => {
    const fetchSalaryData = async () => {
      try {
        const response = await axios.get('http://localhost:8080/api/v1/employee/salary/all', {
          headers: { Authorization: `Bearer ${localStorage.getItem('token')}` }
        });
        setSalaryData(response.data);
      } catch (error) {
        alert('Failed to fetch salary data.');
      }
    };
    fetchSalaryData();
  }, []);

  const downloadSalarySlip = (salary) => {
    const doc = new jsPDF();
    doc.text(`Salary Slip - ${salary.month}`, 10, 10);
    doc.text(`Amount: ${salary.amount}`, 10, 20);
    doc.text(`Date: ${salary.date}`, 10, 30);
    doc.save(`${salary.month}_salary_slip.pdf`);
  };

  return (
    <div className="p-4">
      <h1 className="text-2xl font-bold mb-4">Salary Dashboard</h1>
      <table className="table-auto w-full border-collapse border border-gray-200">
        <thead>
          <tr>
            <th className="border border-gray-200 p-2">Month</th>
            <th className="border border-gray-200 p-2">Amount</th>
            <th className="border border-gray-200 p-2">Actions</th>
          </tr>
        </thead>
        <tbody>
          {salaryData.map((salary) => (
            <tr key={salary.month}>
              <td className="border border-gray-200 p-2">{salary.month}</td>
              <td className="border border-gray-200 p-2">{salary.amount}</td>
              <td className="border border-gray-200 p-2">
                <button
                  onClick={() => downloadSalarySlip(salary)}
                  className="bg-green-500 text-white p-1"
                >
                  Download Slip
                </button>
              </td>
            </tr>
          ))}
        </tbody>
      </table>
    </div>
  );
};

export default SalaryDashboard;
