import { PieChart, Pie, Cell, Tooltip } from 'recharts';

export default function DonutChart({ data }) {
  console.log({ data });

  // Extract values from the data
  const takeHome = parseFloat(data.takeHome);
  const deductions = parseFloat(data.deductions);
  const grossPay = parseFloat(data.grossPay);

  // Calculate the percentages
  const total = takeHome + deductions + grossPay;
  const takeHomePercentage = (takeHome / total) * 100;
  const deductionsPercentage = (deductions / total) * 100;
  const grossPayPercentage = (grossPay / total) * 100;

  // Prepare chart data
  const chartData = [
    { name: 'Take Home', value: takeHomePercentage },
    { name: 'Deductions', value: deductionsPercentage },
    { name: 'Gross Pay', value: grossPayPercentage }
  ];

  const COLORS = ['#4CAF50', '#F44336', '#2196F3'];

  return (
    <div className="flex justify-center items-center">
      <PieChart width={200} height={200}>
        <Pie
          data={chartData}
          dataKey="value"
          innerRadius={60}
          outerRadius={80}
          paddingAngle={5}
          startAngle={90}
          endAngle={450}
        >
          {chartData.map((entry, index) => (
            <Cell key={`cell-${index}`} fill={COLORS[index]} />
          ))}
        </Pie>
        <Tooltip
          formatter={(value) => `${value.toFixed(2)}%`}
          labelFormatter={(name) => `Salary Breakdown: ${name}`}
        />
      </PieChart>
    </div>
  );
}
