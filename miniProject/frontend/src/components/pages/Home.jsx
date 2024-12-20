import { useState, useEffect } from 'react';
import { useNavigate } from 'react-router-dom';
import { useUserDetails } from '../../hooks/useUsersDetails';
import HomeUI from '../Presentation/HomeUI';

export default function Home() {
  const { salaryData } = useUserDetails();
  const [selectedMonth, setSelectedMonth] = useState(0);
  const [showAllDetails, setShowAllDetails] = useState(false);
  const { userDetails } = useUserDetails();
  const navigate = useNavigate();

  // Get the current month data on initial load (only when salaryData changes)
  useEffect(() => {
    if (salaryData.length > 0 && !selectedMonth) {
      const currentMonthIndex = salaryData.findIndex(
        (item) => new Date(item.payment_date).getMonth() === new Date().getMonth()
      );
      setSelectedMonth(salaryData[currentMonthIndex >= 0 ? currentMonthIndex : 0]);
    }
  }, [salaryData, selectedMonth]);

  const handleViewPayslip = () => {
    navigate('/salary-details');
  };

  return (
    <HomeUI
      salaryData={salaryData}
      selectedMonth={selectedMonth}
      setSelectedMonth={setSelectedMonth}
      showAllDetails={showAllDetails}
      setShowAllDetails={setShowAllDetails}
      handleViewPayslip={handleViewPayslip}
      userDetails={userDetails}
    />
  );
}
