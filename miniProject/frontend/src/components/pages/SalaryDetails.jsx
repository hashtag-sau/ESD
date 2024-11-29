import PayslipCard from '../Payslip/PayslipCard';
import PayslipDetails from '../Payslip/PayslipDetails';
import useSalaryDetails from '../../hooks/useSalaryDetails';

const SalaryDetails = () => {
  const {
    salaryData,
    financialYears,
    currentYear,
    selectedMonth,
    setCurrentYear,
    setSelectedMonth
  } = useSalaryDetails();

  const filteredData = salaryData.filter((salary) => salary.getFinancialYear() === currentYear);

  return (
    <div className="p-8 bg-gray-50 min-h-screen">
      <header className="flex justify-between items-center mb-6">
        <div>
          <h1 className="text-2xl font-bold">Salary Details</h1>
          <p className="text-gray-600">Review your payslips for previous months</p>
        </div>
        <select
          value={currentYear}
          onChange={(e) => setCurrentYear(e.target.value)}
          className="px-4 py-2 border rounded-md"
        >
          {financialYears.map((year) => (
            <option key={year} value={year}>
              {year}
            </option>
          ))}
        </select>
      </header>
      <section className="grid grid-cols-2 gap-6">
        <div className="space-y-4">
          {filteredData.map((salary) => (
            <PayslipCard
              key={salary.paymentDate}
              month={salary.getMonthName()}
              takeHome={salary.takeHome}
              onClick={() => setSelectedMonth(salary)}
            />
          ))}
        </div>
        <PayslipDetails salary={selectedMonth} />
      </section>
    </div>
  );
};

export default SalaryDetails;
