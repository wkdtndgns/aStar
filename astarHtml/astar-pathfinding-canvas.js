
var canvas = null;
var ctx = null;
var spritesheet = null;
var spritesheetLoaded = false;

var world = [[]];

// 가로
var worldWidth = 10;
// 높이
var worldHeight = 7;

// 픽셀타일크기(이미지)
var tileWidth = 32;
var tileHeight = 32;

// 경로의 시작점과 끝점
var pathStart = [worldWidth, worldHeight];
var pathEnd = [0, 0];
var currentPath = [];

// 예외처리
if (typeof console == "undefined") var console = {
    log: function () {
    }
};

function onload() {
    console.log('Page loaded.');
    canvas = document.getElementById('gameCanvas');
    canvas.width = worldWidth * tileWidth;
    canvas.height = worldHeight * tileHeight;
    canvas.addEventListener("click", canvasClick, false);
    if (!canvas) alert('Blah!');
    ctx = canvas.getContext("2d");
    if (!ctx) alert('Hmm!');
    spritesheet = new Image();
    spritesheet.src = 'spritesheet.png';

    //spritesheet.src = 'data:image/png;base64,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';
    spritesheet.onload = loaded;
}


function loaded() {
    console.log('Spritesheet loaded.');

    spritesheetLoaded = true;
    createWorld();
}

function createWorld() {
    console.log('Creating world...');

    for (var x = 0; x < worldWidth; x++) {
        world[x] = [];

        for (var y = 0; y < worldHeight; y++) {
            world[x][y] = 0;
        }
    }

    redraw();
}

function resizeworld(){
    
}

function redraw() {
    if (!spritesheetLoaded) return;

    console.log('redrawing...');

    var spriteNum = 0;

    ctx.fillStyle = '#000000';
    ctx.fillRect(0, 0, canvas.width, canvas.height);

    for (var x = 0; x < worldWidth; x++) {
        for (var y = 0; y < worldHeight; y++) {

            // 그릴 이미지 고르기
            switch (world[x][y]) {
                case 1:
                    spriteNum = 1;
                    break;
                default:
                    spriteNum = 0;
                    break;
            }
            ctx.drawImage(spritesheet,
                spriteNum * tileWidth, 0,
                tileWidth, tileHeight,
                x * tileWidth, y * tileHeight,
                tileWidth, tileHeight);
        }
    }

    for (rp = 0; rp < currentPath.length; rp++) {

        switch (rp) {
            case 0:
                spriteNum = 2; // start
                break;
            case currentPath.length - 1:
                spriteNum = 3; // end
                break;
            default:
                spriteNum = 4; // path node
                break;
        }
        ctx.drawImage(spritesheet,
            spriteNum * tileWidth, 0,
            tileWidth, tileHeight,
            currentPath[rp][0] * tileWidth,
            currentPath[rp][1] * tileHeight,
            tileWidth, tileHeight);
    }
}

// 클릭할때
function canvasClick(e) {
    var x;
    var y;

    if (e.pageX != undefined && e.pageY != undefined) {
        x = e.pageX;
        y = e.pageY;
    } else {
        x = e.clientX + document.body.scrollLeft +
            document.documentElement.scrollLeft;
        y = e.clientY + document.body.scrollTop +
            document.documentElement.scrollTop;
    }

    x -= canvas.offsetLeft;
    y -= canvas.offsetTop;

    var cell =
        [
            Math.floor(x / tileWidth),
            Math.floor(y / tileHeight)
        ];


    pathStart = pathEnd;
    pathEnd = cell;

    console.log('pathStart ', pathStart);
    console.log('pathEnd ', pathEnd);

    var iDirection = $('#selOption').val();
    console.log(iDirection);
    if (iDirection == 4) {
        currentPath = findPath(world, pathStart, pathEnd);
        redraw();
    } else {
        if (pathStart[0] == pathEnd[0] && pathStart[1] == pathEnd[1]) {
            currentPath = [pathStart];
            redraw();
        } else {
            $.ajax({
                url: "http://localhost:8080/aStar",
                type: "POST",
                data: JSON.stringify({
                    world: world,
                    pathStart: pathStart,
                    pathEnd: pathEnd
                }),
                contentType: "application/json",
                dataType: "json",
                success: function (response) {
                    // 서버로부터 받은 응답 데이터를 처리하는 코드
                    console.log(response);
                    // 응답 데이터를 활용하여 필요한 작업 수행
                    currentPath = response
                    redraw();
                },
                error: function (xhr, status, error) {
                    // AJAX 요청이 실패한 경우의 처리 코드
                    console.error(error);
                }
            });
        }
    }
}

function findPath(world, pathStart, pathEnd) {
 
    var abs = Math.abs;
    var max = Math.max;
    var pow = Math.pow;
    var sqrt = Math.sqrt;

    var maxWalkableTileNum = 0;

    // 세계 배열이 정사각형이 될 것으로 예상하여 높이와 너비가 같아야함
     // 세계가 직사각형이라면 배열을 더미 값으로 채워 빈 공간을 채웁니다.
    var worldWidth = world[0].length;
    var worldHeight = world.length;
    var worldSize = worldWidth * worldHeight;

    // 사용하는 휴리스틱 값 => default는 맨해탄
    var distanceFunction = ManhattanDistance;
    var findNeighbours = function () {
    }; 


    function ManhattanDistance(Point, Goal) {       // 상하좌우 이동
        return abs(Point.x - Goal.x) + abs(Point.y - Goal.y);
    }

    function DiagonalDistance(Point, Goal) {	// 대각선이동
        return max(abs(Point.x - Goal.x), abs(Point.y - Goal.y));
    }

    function EuclideanDistance(Point, Goal) {	
        return sqrt(pow(Point.x - Goal.x, 2) + pow(Point.y - Goal.y, 2));
    }

    function Neighbours(x, y) {
        var N = y - 1,
            S = y + 1,
            E = x + 1,
            W = x - 1,
            myN = N > -1 && canWalkHere(x, N),
            myS = S < worldHeight && canWalkHere(x, S),
            myE = E < worldWidth && canWalkHere(E, y),
            myW = W > -1 && canWalkHere(W, y),
            result = [];
        if (myN)
            result.push({x: x, y: N});
        if (myE)
            result.push({x: E, y: y});
        if (myS)
            result.push({x: x, y: S});
        if (myW)
            result.push({x: W, y: y});
        findNeighbours(myN, myS, myE, myW, N, S, E, W, result);
        return result;
    }

    function DiagonalNeighbours(myN, myS, myE, myW, N, S, E, W, result) {
        if (myN) {
            if (myE && canWalkHere(E, N))
                result.push({x: E, y: N});
            if (myW && canWalkHere(W, N))
                result.push({x: W, y: N});
        }
        if (myS) {
            if (myE && canWalkHere(E, S))
                result.push({x: E, y: S});
            if (myW && canWalkHere(W, S))
                result.push({x: W, y: S});
        }
    }

    function DiagonalNeighboursFree(myN, myS, myE, myW, N, S, E, W, result) {
        myN = N > -1;
        myS = S < worldHeight;
        myE = E < worldWidth;
        myW = W > -1;
        if (myE) {
            if (myN && canWalkHere(E, N))
                result.push({x: E, y: N});
            if (myS && canWalkHere(E, S))
                result.push({x: E, y: S});
        }
        if (myW) {
            if (myN && canWalkHere(W, N))
                result.push({x: W, y: N});
            if (myS && canWalkHere(W, S))
                result.push({x: W, y: S});
        }
    }

    // 갈수 있는 곳인지 확인
    function canWalkHere(x, y) {
        return ((world[x] != null) &&
            (world[x][y] != null) &&
            (world[x][y] <= maxWalkableTileNum));
    };

    // Node 속성을 가진 새 객체를 반환
     // 경로 비용 등을 저장하기 위해 calculatePath 함수에서 사용
    function Node(Parent, Point) {
        var newNode = {
            Parent: Parent,
            value: Point.x + (Point.y * worldWidth),
            // 이 노드의 위치
            x: Point.x,
            y: Point.y,
            // 이 노드를 사용하는 전체 경로의 예상 휴리스틱 비용
            f: 0,
            // 시작점에서 이 노드까지의 거리비용
            g: 0
        };

        return newNode;
    }

    // // 경로 기능, AStar 알고리즘 연산 실행
    function calculatePath() {
  
        var mypathStart = Node(null, {x: pathStart[0], y: pathStart[1]});
        var mypathEnd = Node(null, {x: pathEnd[0], y: pathEnd[1]});
   
        var AStar = new Array(worldSize);
        var Open = [mypathStart];
        var Closed = [];
        var result = [];
        var myNeighbours;

        var myNode;
        var myPath;
        var length, max, min, i, j;

        while (length = Open.length) {
            max = worldSize;
            min = -1;
            for (i = 0; i < length; i++) {
                if (Open[i].f < max) {
                    max = Open[i].f;
                    min = i;
                }
            }
            myNode = Open.splice(min, 1)[0];
            // 도착지점인지 확인
            if (myNode.value === mypathEnd.value) {
                myPath = Closed[Closed.push(myNode) - 1];
                do {
                    result.push([myPath.x, myPath.y]);
                }
                while (myPath = myPath.Parent);
                // 초기화
                AStar = Closed = Open = [];
                // 시작부터 끝까지 return 하기 원함 (뒤집어서)
                result.reverse();
            } else // 도착지점이 아닐때
            {
                // 이웃노드 탐색
                myNeighbours = Neighbours(myNode.x, myNode.y);
                for (i = 0, j = myNeighbours.length; i < j; i++) {
                    myPath = Node(myNode, myNeighbours[i]);
                    if (!AStar[myPath.value]) {
                        myPath.g = myNode.g + distanceFunction(myNeighbours[i], myNode);
                        myPath.f = myPath.g + distanceFunction(myNeighbours[i], mypathEnd);

                        Open.push(myPath);

                        AStar[myPath.value] = true;
                    }
                }
                Closed.push(myNode);
            }
        }
        return result;
    }

    // 좌표를 배열로 반환
    // 가능한 경로가 없으면 비어 있음
    return calculatePath();

} 

