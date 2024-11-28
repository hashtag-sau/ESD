export default function PayslipCard({ month, takeHome, onClick }) {
  return (
    <div
      className="bg-white shadow-md p-6 rounded-md cursor-pointer hover:bg-gray-100"
      onClick={onClick}
    >
      <h3 className="text-lg font-semibold">{month}</h3>
      <p className="text-gray-600">Take Home: {takeHome}</p>
    </div>
  );
}
