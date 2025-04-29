export type Employee = {
    seq: number;
    accountId: string,
    name: string;
    deptCode: string,
    deptName: string,
    positionCode: string,
    positionName: string,
    startDate: string,
    endDate: string | undefined,
}