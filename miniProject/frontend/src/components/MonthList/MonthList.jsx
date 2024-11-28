// components/UserList/UserList.jsx

import UserCard from '../Presentation/UserCard';
import { useUserDetails } from '../../hooks/useUsersDetails';
import { getMonthName } from '../../utils/dateUtil';

const MonthList = ({ selectedMonth, setSelectedMonth }) => {
  const { userDetails, salaryData } = useUserDetails();

  return (
    <>
      <div className="mb-6">
        <div className="flex gap-4 overflow-x-auto">
          {salaryData?.map((item) => (
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
