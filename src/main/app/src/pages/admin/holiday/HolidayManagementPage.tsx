import {useState} from "react";
import {GridColDef} from '@mui/x-data-grid';
import axios from "axios";
import {API_URL} from "../../../constants/constants.ts";
import {Page} from "../../../features/common/types/common.types.ts";
import {Button, ButtonGroup} from "@mui/material";
import CustomPagerGrid from "../../../features/common/components/CustomPagerGrid.tsx";
import SearchForm from "../../../features/admin/holiday/components/SearchForm.tsx";
import {Holiday} from "../../../features/admin/holiday/types/holiday.types.ts";
import SaveModal from "../../../features/admin/holiday/components/SaveModal.tsx";
import SyncModal from "../../../features/admin/holiday/components/SyncModal.tsx";

const HolidayManagementPage = () => {

    const [isOpenModal, setIsOpenModal] = useState<boolean>(false);
    const [isOpenSyncModal, setIsOpenSyncModal] = useState<boolean>(false);
    const [selectedRow, setSelectedRow] = useState<Holiday | undefined>();

    const columns: GridColDef[] = [
        { field: 'id', headerName: 'id', width: 100 },
        { field: 'holidayDate', headerName: '휴무일', width: 100 },
        { field: 'typeName', headerName: '휴무구분', width: 100 },
        { field: 'description', headerName: '비고', width: 200 },
    ];
    const [data, setData] = useState<Page<Holiday>>();

    return (
        <div>
            <SearchForm onSearch={onSearch} />

            <ButtonGroup>
                <Button variant={"contained"} onClick={() => setIsOpenModal(true)}>
                    {!selectedRow ? "추가" : "변경"}
                </Button>
                {selectedRow && <Button variant={"outlined"} onClick={() => handleDelete(selectedRow.id)}>제거</Button>}
                <Button variant={"contained"} onClick={() => setIsOpenSyncModal(true)}>동기화</Button>
            </ButtonGroup>
            <CustomPagerGrid
                columns={columns}
                data={data}
                getRowId={(row) => row.id}
                onSearch={onSearch}
                onSelectedRow={(row) => setSelectedRow(row)}
            />


            <SaveModal openModal={isOpenModal} setOpenModal={setIsOpenModal} onSearch={onSearch} initialData={selectedRow} />
            <SyncModal openModal={isOpenSyncModal} setOpenModal={setIsOpenSyncModal} onSearch={onSearch} />
        </div>
    );

    function onSearch(page: number, yearMonth?: string) {

        const data = {page, yearMonth};

        axios.get<Page<Holiday>>(`${API_URL}/api/admin/holiday`, {params: data})
            .then(response => {
                console.log(response);
                setData(response.data);
            })
            .catch(error => {
                console.log(error);
            });
    }

    function handleDelete(id: number) {
        axios.delete(`${API_URL}/api/admin/holiday/${id}`)
            .then(() => {
                alert("삭제되었습니다.");
                onSearch(0);
            })
            .catch(error => {
                console.log(error);
            });
    }
}



export default HolidayManagementPage;