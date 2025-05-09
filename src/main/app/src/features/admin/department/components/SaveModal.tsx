import {Button, TextField} from "@mui/material";
import SaveButtonGroup from "../../../common/components/SaveButtonGroup.tsx";
import CustomModal from "../../../common/components/CustomModal.tsx";
import {useForm} from "react-hook-form";
import axios from "axios";
import {API_URL} from "../../../../constants/constants.ts";
import {useEffect} from "react";
import {Department} from "../types/department.types.ts";

const SaveModal = (props: Props) => {

    const {openModal, setOpenModal, onSearch, initialData} = props;
    const {
        register, getValues, reset
    } = useForm<DepartmentSaveForm>();

    useEffect(() => {
        reset({
            code: initialData?.code,
            name: initialData?.name,
            upperCode: initialData?.upperCode,
            startDate: initialData?.startDate,
            endDate: initialData?.endDate,
        });
    }, [initialData]);

    return (
        <CustomModal openModal={openModal}>
            <TextField
                {...register("code")}
                label={"부서코드"}
                variant={"standard"}
                fullWidth={true}
                disabled={!!initialData}
            />
            <TextField
                {...register("name")}
                label={"부서명"}
                variant={"standard"}
                fullWidth={true}
            />
            <TextField
                {...register("upperCode")}
                label={"상위부서코드"}
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
        axios.post(`${API_URL}/api/admin/department`, data)
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
    initialData: Department | undefined,
}

type DepartmentSaveForm = {
    code: string,
    name: string,
    upperCode?: string,
    startDate: string,
    endDate?: string,
}

export default SaveModal;