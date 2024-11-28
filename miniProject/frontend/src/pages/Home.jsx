import { useState, useEffect } from 'react';
import MonthList from '../components/MonthList/MonthList';
import DonutChart from '../components/DonutChart';
import { useNavigate } from 'react-router-dom';
import { useUserDetails } from '../hooks/useUsersDetails';

export default function Home() {
  const { salaryData } = useUserDetails();
  const [selectedMonth, setSelectedMonth] = useState(null);
  const [showAllDetails, setShowAllDetails] = useState(false);
  const navigate = useNavigate();

  // Get the current month data on initial load
  useEffect(() => {
    if (salaryData.length > 0) {
      const currentMonthIndex = salaryData.findIndex(
        (item) => new Date(item.payment_date).getMonth() === new Date().getMonth()
      );
      setSelectedMonth(salaryData[currentMonthIndex >= 0 ? currentMonthIndex : 0]);
    }
  }, [salaryData]);

  const handleViewPayslip = () => {
    navigate('/salary-details');
  };

  return (
    <div className="p-8 bg-gray-50 min-h-screen">
      {/* User Header and Month Navigation */}
      <MonthList selectedMonth={selectedMonth} setSelectedMonth={setSelectedMonth} />

      {/* Payslip & Tax Summary Sections */}
      <section className="grid grid-cols-2 gap-6">
        {/* Payslip Overview */}
        <div className="bg-white shadow-md p-6 rounded-md">
          <h2 className="text-lg font-bold mb-4">Your Payslips</h2>
          {selectedMonth && (
            <>
              <DonutChart data={selectedMonth} />
              <div className="mt-4 flex justify-between">
                <div className="text-gray-600">
                  <strong>Take Home:</strong> {showAllDetails ? selectedMonth.takeHome : '******'}
                </div>
                <div className="text-gray-600">
                  <strong>Deductions:</strong>{' '}
                  {showAllDetails ? selectedMonth.deductions : '******'}
                </div>
                <div className="text-gray-600">
                  <strong>Gross Pay:</strong> {showAllDetails ? selectedMonth.grossPay : '******'}
                </div>
              </div>
              <button
                className="mt-4 bg-blue-600 text-white px-4 py-2 rounded-md"
                onClick={handleViewPayslip}
              >
                View Payslip →
              </button>
            </>
          )}
        </div>

        {/* Tax Summary */}
        <div className="bg-white shadow-md p-6 rounded-md">
          <h2 className="text-lg font-bold mb-4">Tax Summary: FY 2024 - 2025</h2>
          {selectedMonth && (
            <>
              <p className="mb-2">
                <strong>Total Annual Tax:</strong> {showAllDetails ? `₹10,000` : '******'}
              </p>
              <div className="bg-gray-200 rounded-md h-4 mb-2 relative">
                <div
                  className="absolute top-0 left-0 bg-blue-500 h-4"
                  style={{
                    width: `${(selectedMonth.paidTax / selectedMonth.totalTax) * 100}%`
                  }}
                ></div>
              </div>
              <p>
                <span className="text-blue-600">Paid:</span> {showAllDetails ? `₹5,500` : '******'}
              </p>
              <p>
                <span className="text-red-600">Remaining:</span>{' '}
                {showAllDetails ? `₹4,500` : '******'}
              </p>
            </>
          )}
        </div>
      </section>

      {/* Toggle Button */}
      <button
        className="mt-4 bg-gray-600 text-white px-4 py-2 rounded-md"
        onClick={() => setShowAllDetails(!showAllDetails)}
      >
        {showAllDetails ? 'Hide Details' : 'Show Details'}
      </button>
    </div>
  );
}
