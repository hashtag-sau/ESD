// src/components/SalaryDashboard.js
import React from 'react';
import jsPDF from 'jspdf';
import useSalaryData from '../hooks/useSalaryData'; // Import custom hook

const SalaryDashboard = () => {
  // Use custom hook for salary data
  const { salaryData, loading, error } = useSalaryData();
  console.log(salaryData);

  const downloadSalarySlip = (salary) => {
    const doc = new jsPDF();
    doc.text(`Salary Slip - ${salary.month}`, 10, 10);
    doc.text(`Amount: ${salary.amount}`, 10, 20);
    doc.text(`Date: ${salary.date}`, 10, 30);
    doc.save(`${salary.month}_salary_slip.pdf`);
  };

  if (loading) {
    return <div>Loading...</div>; // Displays when loading is true
  }

  if (error) {
    return <div>{error}</div>; // Displays when there is an error
  }

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
