import { useUserDetails } from '../../hooks/useUsersDetails';
import { getMonthName } from '../../utils/dateUtil';

const MonthList = ({ selectedMonth, setSelectedMonth, salaryData }) => {
  console.log({ selectedMonth, setSelectedMonth });

  //const { userDetails, salaryData } = useUserDetails();

  // Get the current year
  const currentYear = new Date().getFullYear();

  // Filter salaryData to only include months from the current year
  const filteredSalaryData = salaryData?.filter(
    (item) => new Date(item.payment_date).getFullYear() === currentYear
  );

  return (
    <>
      <div className="mb-6">
        <div className="flex gap-4 overflow-x-auto">
          {filteredSalaryData?.map((item) => (
            <button
              key={item.payment_date}
              className={`px-4 py-2 rounded-md ${selectedMonth?.payment_date === item.payment_date ? 'bg-blue-600 text-white' : 'bg-gray-200 text-gray-600'}`}
              onClick={() => setSelectedMonth(item)}
            >
              {getMonthName(item.payment_date)}
            </button>
          ))}
        </div>
      </div>
    </>
  );
};

export default MonthList;
