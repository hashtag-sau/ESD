const PayslipCard = ({ month, takeHome, onClick }) => (
  <div
    onClick={onClick}
    className="cursor-pointer p-4 bg-white shadow rounded-md hover:bg-blue-100 transition"
  >
    <h3 className="font-semibold">{month}</h3>
    <p className="text-gray-600">Take Home: {takeHome}</p>
  </div>
);

export default PayslipCard;
