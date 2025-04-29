import {useState} from "react";
import {GridColDef} from '@mui/x-data-grid';
import axios from "axios";
import {API_URL} from "../../../constants/constants.ts";
import {Page} from "../../../features/common/types/common.types.ts";
import {Employee} from "../../../features/admin/employee/types/employee.types.ts";
import SaveModal from "../../../features/admin/employee/components/SaveModal.tsx";
import {Button, ButtonGroup} from "@mui/material";
import CustomPagerGrid from "../../../features/common/components/CustomPagerGrid.tsx";
import SearchForm from "../../../features/admin/employee/components/SearchForm.tsx";

const EmployeeManagementPage = () => {

    const [isOpenModal, setIsOpenModal] = useState<boolean>(false);
    const [selectedRow, setSelectedRow] = useState<Employee | undefined>();

    const columns: GridColDef[] = [
        { field: 'seq', headerName: '사원번호', width: 100 },
        { field: 'accountId', headerName: '사원ID', width: 100 },
        { field: 'name', headerName: '사원명', width: 150 },
        { field: 'deptCode', headerName: '부서코드', width: 150 },
        { field: 'deptName', headerName: '부서명', width: 150 },
        { field: 'positionCode', headerName: '직책코드', width: 150 },
        { field: 'positionName', headerName: '직책명', width: 100 },
        { field: 'startDate', headerName: '시작일', width: 100 },
        { field: 'endDate', headerName: '종료일', width: 100 },
    ];
    const [data, setData] = useState<Page<Employee>>();

    return (
        <div>
            <SearchForm onSearch={onSearch} />

            <ButtonGroup>
                <Button variant={"contained"} onClick={() => setIsOpenModal(true)}>
                    {!selectedRow ? "추가" : "변경"}
                </Button>
                {selectedRow && <Button variant={"outlined"} onClick={() => handleDelete(selectedRow.seq)}>제거</Button>}
            </ButtonGroup>
            <CustomPagerGrid
                columns={columns}
                data={data}
                getRowId={(row) => row.seq}
                onSearch={onSearch}
                onSelectedRow={(row) => setSelectedRow(row)}
            />

            <SaveModal openModal={isOpenModal} setOpenModal={setIsOpenModal} onSearch={onSearch} initialData={selectedRow} />
        </div>
    );

    function onSearch(page: number, keyword?: string) {

        const data = {page, keyword};

        axios.get<Page<Employee>>(`${API_URL}/api/admin/employee`, {params: data})
            .then(response => {
                console.log(response);
                setData(response.data);
            })
            .catch(error => {
                console.log(error);
            });
    }

    function handleDelete(employeeSeq: number) {
        axios.delete(`${API_URL}/api/admin/employee/${employeeSeq}`)
            .then(() => {
                alert("삭제되었습니다.");
                onSearch(0);
            })
            .catch(error => {
                console.log(error);
            });
    }
}



export default EmployeeManagementPage;