import {useState} from "react";
import {Button, ButtonGroup} from "@mui/material";
import {GridColDef} from '@mui/x-data-grid';
import SaveModal from "../../../features/admin/department/components/SaveModal.tsx";
import axios from "axios";
import {API_URL} from "../../../constants/constants.ts";
import CustomPagerGrid from "../../../features/common/components/CustomPagerGrid.tsx";
import {Page} from "../../../features/common/types/common.types.ts";
import SearchForm from "../../../features/admin/department/components/SearchForm.tsx";

const DepartmentManagementPage = () => {

    const [isOpenModal, setIsOpenModal] = useState<boolean>(false);
    const [selectedRow, setSelectedRow] = useState<Department | undefined>();

    const columns: GridColDef[] = [
        { field: 'code', headerName: '부서코드', width: 150 },
        { field: 'name', headerName: '부서명', width: 150 },
        { field: 'upperCode', headerName: '상위부서코드', width: 150 },
        { field: 'startDate', headerName: '시작일', width: 150 },
        { field: 'endDate', headerName: '종료일', width: 150 },
    ];
    const [data, setData] = useState<Page<Department>>();

    return (
        <div>
            <SearchForm onSearch={onSearch} />

            <ButtonGroup>
                <Button variant={"contained"} onClick={() => setIsOpenModal(true)}>
                    {!selectedRow ? "추가" : "변경"}
                </Button>
                {selectedRow && <Button variant={"outlined"} onClick={() => handleDelete(selectedRow.code)}>제거</Button>}
            </ButtonGroup>
            <CustomPagerGrid columns={columns} data={data} onSearch={onSearch} onSelectedRow={(row) => setSelectedRow(row)} />

            <SaveModal openModal={isOpenModal} setOpenModal={setIsOpenModal} onSearch={onSearch} initialData={selectedRow} />
        </div>
    );

    function onSearch(page: number, keyword?: string) {

        const data = {page, keyword};

        axios.get<Page<Department>>(`${API_URL}/api/admin/department`, {params: data})
            .then(response => {
                console.log(response);
                setData(response.data);
            })
            .catch(error => {
                console.log(error);
            });
    }

    function handleDelete(deptCode: string) {
        axios.delete(`${API_URL}/api/admin/department/${deptCode}`)
            .then(() => {
                alert("삭제되었습니다.");
                onSearch(0);
            })
            .catch(error => {
                console.log(error);
            });
    }
}

export type Department = {
    code: string;
    name: string;
    upperCode: string | undefined;
    startDate: string;
    endDate: string | undefined;
}

export default DepartmentManagementPage;