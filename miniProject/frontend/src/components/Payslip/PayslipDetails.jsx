import GeneratePayslipPDF from '../Presentation/GeneratePayslipPDF';

const PayslipDetails = ({ salary }) => {
  if (!salary) return null;

  return (
    <div className="bg-white shadow-md p-6 rounded-md">
      <h2 className="text-lg font-bold">
        Payslip for {salary.getMonthName()} {new Date(salary.paymentDate).getFullYear()}
      </h2>
      <div className="flex justify-between mt-4">
        <div className="text-gray-600">Take Home: {salary.takeHome}</div>
        <div className="text-gray-600">Gross Pay: {salary.grossPay}</div>
      </div>
      {/* Additional Details */}
      <div className="mt-6">
        <h3 className="font-semibold mb-2">Details</h3>
        <table className="min-w-full table-auto">
          {salary.details.map((detail, index) => (
            <tr key={index}>
              <td className="p-2">{detail.name}</td>
              <td className="p-2 text-right">{detail.amount}</td>
            </tr>
          ))}
        </table>
      </div>
      {/* Deductions */}
      <div className="mt-6">
        <h3 className="font-semibold mb-2">Deductions</h3>
        <table className="min-w-full table-auto">
          {salary.deductionsDetails.map((deduction, index) => (
            <tr key={index}>
              <td className="p-2">{deduction.name}</td>
              <td className="p-2 text-right">{deduction.amount}</td>
            </tr>
          ))}
        </table>
      </div>
      <GeneratePayslipPDF selectedMonth={salary} />
    </div>
  );
};

export default PayslipDetails;
