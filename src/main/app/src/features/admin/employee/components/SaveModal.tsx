import {Button, TextField} from "@mui/material";
import SaveButtonGroup from "../../../common/components/SaveButtonGroup.tsx";
import CustomModal from "../../../common/components/CustomModal.tsx";
import {useForm} from "react-hook-form";
import axios from "axios";
import {API_URL} from "../../../../constants/constants.ts";
import {useEffect} from "react";
import {Employee} from "../types/employee.types.ts";

const SaveModal = (props: Props) => {

    const {openModal, setOpenModal, onSearch, initialData} = props;
    const {
        register, getValues, reset
    } = useForm<EmployeeSaveForm>();

    useEffect(() => {
        reset({
            seq: initialData?.seq,
            accountId: initialData?.accountId,
            name: initialData?.name,
            deptCode: initialData?.deptCode,
            startDate: initialData?.startDate,
            endDate: initialData?.endDate,
        });
    }, [initialData]);

    return (
        <CustomModal openModal={openModal}>
            <TextField
                {...register("seq")}
                label={"사원번호"}
                variant={"standard"}
                fullWidth={true}
                disabled={true}
            />
            <TextField
                {...register("accountId")}
                label={"사원ID"}
                variant={"standard"}
                fullWidth={true}
            />
            <TextField
                {...register("name")}
                label={"사원명"}
                variant={"standard"}
                fullWidth={true}
            />
            <TextField
                {...register("deptCode")}
                label={"부서코드"}
                variant={"standard"}
                fullWidth={true}
            />
            <TextField
                {...register("startDate")}
                label={"시작일"}
                type={"date"}
                slotProps={{ inputLabel: { shrink: true } }}
                variant={"standard"}
                fullWidth={true}
            />
            <TextField
                {...register("endDate")}
                label={"종료일"}
                placeholder={"YYYY-MM-DD"}
                type={"date"}
                slotProps={{ inputLabel: { shrink: true } }}
                variant={"standard"}
                fullWidth={true}
            />
            <SaveButtonGroup>
                <Button variant={"outlined"} onClick={close}>취소</Button>
                <Button variant={"contained"} onClick={save}>저장</Button>
            </SaveButtonGroup>

        </CustomModal>
    );

    function close() {
        reset();
        setOpenModal(false);
    }

    function save() {
        const data = getValues();
        console.log(data);
        axios.post(`${API_URL}/api/admin/employee`, data)
            .then(() => {
                alert("저장되었습니다.");
                reset();
                setOpenModal(false);
                onSearch(0);
            })
            .catch(() => {
                alert("저장에 실패했습니다.");
            })
    }
}

type Props = {
    openModal: boolean,
    setOpenModal: (open: boolean) => void,
    onSearch: (page: number) => void,
    initialData: Employee | undefined,
}

type EmployeeSaveForm = {
    seq: number,
    accountId: string,
    name: string,
    deptCode: string,
    startDate: string,
    endDate?: string,
}

export default SaveModal;