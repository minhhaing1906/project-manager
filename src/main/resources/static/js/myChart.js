// convert cryptic chartData to JSON
// '[{"stage":"COMPLETED","stageCount":1},{"stage":"INPROGRESS","stageCount":2},{"stage":"NOTSTARTED","stageCount":1}]'
function decodeHtml(html) {
    const txt = document.createElement("textarea");
    txt.innerHTML = html;
    return txt.value;
}

const chartDataStr = decodeHtml(chartData);
const chartJsonArr = JSON.parse(chartDataStr);

const arrLength = chartJsonArr.length;
const stages = [];
const countStage = [];

for (let i = 0; i < arrLength; i++) {
    stages.push(chartJsonArr[i].stage);
    countStage.push(chartJsonArr[i].stageCount);
}

new Chart(document.getElementById("myPieChart"),{
    // The type of chart we want to create
    type: 'pie',

    // The data for our dataset
    data: {
        labels: stages,
        datasets: [{
            label: 'My First dataset',
            backgroundColor: ['red', 'lightgreen', 'blue'],
            data: countStage
        }]
    },

    // Configuration options go here
    options: {
        title: {
            display: true,
            text: 'Project Statuses'
        }
    }
});


