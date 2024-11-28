import MonthList from '../MonthList/MonthList';
const HomeUI = ({
  salaryData,
  selectedMonth,
  setSelectedMonth,
  showAllDetails,
  setShowAllDetails
}) => {
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
};

export default HomeUI;
