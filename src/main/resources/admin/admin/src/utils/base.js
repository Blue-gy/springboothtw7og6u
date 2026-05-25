const base = {
    get() {
        return {
            url : "http://localhost:8080/springboothtw7og6u/",
            name: "springboothtw7og6u",
            // 退出到首页链接
            indexUrl: 'http://localhost:8080/springboothtw7og6u/front/dist/index.html'
        };
    },
    getProjectName(){
        return {
            projectName: "校园场地与设备预约管理系统"
        } 
    }
}
export default base
