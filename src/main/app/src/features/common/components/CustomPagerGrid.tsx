import {DataGrid, GridColDef} from "@mui/x-data-grid";
import Paper from "@mui/material/Paper";
import {useEffect, useState} from "react";

const CustomPagerGrid = (props: Props) => {

    const { columns, data, onSearch, onSelectedRow } = props;

    useEffect(() => {
        if(data) {
            setContents(data.content);
            setPage(data.number);
            setSize(data.size);
            setTotalElements(data.totalElements);
        }
    }, [data]);

    const [contents, setContents] = useState<any[]>([]);
    const [page, setPage] = useState<number>(0);
    const [size, setSize] = useState<number>(10);
    const [totalElements, setTotalElements] = useState<number>(0);

    useEffect(() => {
        onSearch(0);
    }, []);

    return (
        <Paper sx={{ height: 600, width: '100%' }}>
            <DataGrid
                columns={columns}
                rows={contents}
                getRowId={(row => row.code)}
                rowCount={totalElements}
                paginationMode={"server"}
                paginationModel={{ page: page, pageSize: size }}
                onPaginationModelChange={(pagination) => onSearch(pagination.page)}
                onRowSelectionModelChange={(row) => {
                    const id = row.ids.values().next().value;
                    const selected = contents.find((item) => item.code === id);
                    onSelectedRow(selected);
                }}
                sx={{ border: 0 }}
            />
        </Paper>
    )
}

type Props = {
    columns: GridColDef[],
    data: any,
    onSearch: (page: number) => void,
    onSelectedRow: (row: any) => void,
}

export default CustomPagerGrid;