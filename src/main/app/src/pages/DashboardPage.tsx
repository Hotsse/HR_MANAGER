import {Button} from "@mui/material";
import {useEffect, useState} from "react";
import axios from "axios";
import {API_URL} from "../constants/constants.ts";
import {useForm} from "react-hook-form";

const DashboardPage = () => {

    const {
        getValues,
    } = useForm<WorkLogSaveForm>({
        defaultValues: {
            accountId: "heyho.se", //TODO: 로그인 기능 붙인 후에 토큰 기반으로 수정
        }
    });

    const [workStatus, setWorkStatus] = useState<EmployeeWorkStatus>({
        workStatus: "",
        isLateClockIn: false,
        workLogs: [],
    });

    useEffect(() => {
        getTodayWorkLog();
    }, []);

    return (
        <div>
            {renderClockInOutButton()}
        </div>
    );

    function renderClockInOutButton() {
        if(workStatus.workStatus === "BEFORE_WORK") {
            return <Button variant={"contained"} size={"large"} onClick={() => clockIn()}>출근</Button>;
        } else if(workStatus.workStatus === "WORKING") {
            return <Button variant={"contained"} size={"large"} onClick={() => clockOut()}>퇴근</Button>;
        } else if(workStatus.workStatus === "AFTER_WORK") {
            return <Button variant={"contained"} size={"large"} onClick={() => clockIn()}>재출근</Button>;
        } else {
            return <></>;
        }
    }

    function getTodayWorkLog() {

        const accountId = getValues("accountId");
        const data = {accountId};

        axios.get<EmployeeWorkStatus>(`${API_URL}/api/work-log/today`, {params: data})
            .then(response => {
                console.log(response);
                setWorkStatus(response.data);
            })
            .catch(error => {
                console.log(error);
            });
    }

    function getReason() {
        let message = "";
        if (workStatus.workStatus === "AFTER_WORK") {
            message = "재출근 사유를 입력해 주세요";
        } else if (workStatus.isLateClockIn) {
            message = "지각 사유를 입력해 주세요";
        }

        let reason: string | null = null;
        if (message) {
            reason = prompt(message);
        }
        return reason;
    }

    function clockIn() {

        const accountId = getValues("accountId");
        const reason = getReason();
        const data = {accountId, reason};

        axios.post(`${API_URL}/api/work-log/clock-in`, data)
            .then(() => {
                alert("출근했습니다.");
                getTodayWorkLog();
            })
            .catch(() => {
                alert("출근에 실패했습니다.");
            })
    }

    function clockOut() {

        const accountId = getValues("accountId");
        const data = {accountId};

        axios.post(`${API_URL}/api/work-log/clock-out`, data)
            .then(() => {
                alert("퇴근했습니다.");
                getTodayWorkLog();
            })
            .catch(() => {
                alert("퇴근에 실패했습니다.");
            })
    }
}

type EmployeeWorkStatus = {
    workStatus: string,
    isLateClockIn: boolean,
    workLogs: any[],
}

type WorkLogSaveForm = {
    accountId: string,
}

export default DashboardPage;