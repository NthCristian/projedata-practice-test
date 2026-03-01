export default function Table({ table }: { table: Array<unknown> }) {
  const keys = Array.from(
    new Set(table.flatMap((obj) => Object.keys(obj as object))),
  );

  return (
    <table className="outline border-collapse [&_th,&_td]:py-3 [&_th,&_td]:px-8 **:whitespace-nowrap p-6 rounded-md *:[&>tr]:*:w-25">
      <thead>
        <tr className="border-b ">
          {keys.map((key) => (
            <th key={key}>{key}</th>
          ))}
        </tr>
      </thead>
      <tbody>
        {table.map((obj, i) => (
          <tr key={i}>
            {keys.map((key) => (
              <td key={key} className="text-center">
                {(obj as Record<string, unknown>)[key] as string}
              </td>
            ))}
          </tr>
        ))}
      </tbody>
    </table>
  );
}
