/**
 * test2
 *
 * @author shjang02 < shjang02@simplexi.com >
 * @since 2023. 05. 17.
 */
// A $( document ).ready() block.
$(document).ready(function () {
    $('#txtX').val(worldWidth);
    $('#txtY').val(worldHeight);
    $('#txtX, #txtY').change(function () {
        worldWidth = $('#txtX').val();
        worldHeight = $('#txtY').val();
        canvas.width = worldWidth * tileWidth;
        canvas.height = worldHeight * tileHeight;
        createWorld();
    });


    /**
     * 장애물 랜덤 생성
     */
    $('#btnRandomWall').click(function () {
        for (var x = 0; x < worldWidth; x++) {
            world[x] = [];
            for (var y = 0; y < worldHeight; y++) {
                world[x][y] = 0;
            }
        }
        for (var x = 0; x < worldWidth; x++) {
            for (var y = 0; y < worldHeight; y++) {

                if (Math.random() > 0.75) world[x][y] = 1;
            }
        }

        init();
    });

    /**
     * 장애물 초기화
     */
    $('#btnResetWall').click(function () {
        for (var x = 0; x < worldWidth; x++) {
            world[x] = [];
            for (var y = 0; y < worldHeight; y++) {
                world[x][y] = 0;
            }
        }

        init();
    });

    /**
     * 경로 초기화
     */
    $('#btnRest').click(function () {
        init();
    })

    $('#btnHistory').click(function () {
        let aHistory = aStarHistory;
        const f = function (aRow) {
            $.each(aRow, function (j, arr) {
                ctx.drawImage(spritesheet,
                    4 * tileWidth, 0,
                    tileWidth, tileHeight,
                    arr[0] * tileWidth,
                    arr[1] * tileHeight,
                    tileWidth, tileHeight);
            });
        }

        let term = 300;
        if (aHistory.length > 10) {
            term = 100;
        }

        $.each(aHistory, function (i, aRow) {
            setTimeout(f, term * i, aRow);
            if (i === aHistory.length - 1) {
                setTimeout(redraw, term * (i + 1));
            }
        });
    })

    /**
     * 짱구 이동
     */
    $('#btnMover').click(function () {
        function fMover(rp){
            var start = rp;
            for (var x = 0; x < worldWidth; x++) {
                for (var y = 0; y < worldHeight; y++) {

                    // choose a sprite to draw
                    switch (world[x][y]) {
                        case 1:
                            spriteNum = 1;
                            break;
                        default:
                            spriteNum = 0;
                            break;
                    }


                    // draw it
                    // ctx.drawImage(img,sx,sy,swidth,sheight,x,y,width,height);
                    ctx.drawImage(spritesheet,
                        spriteNum * tileWidth, 0,
                        tileWidth, tileHeight,
                        x * tileWidth, y * tileHeight,
                        tileWidth, tileHeight);
                }
            }

            for (rp; rp < currentPath.length; rp++) {
                switch (rp) {
                    case start:
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

        var term = 300;
        for (rp = 0; rp < currentPath.length; rp++) {
            setTimeout(fMover, term * rp, rp);

            if(currentPath.length-1 === rp){
                setTimeout(function () {$('#imgFind').fadeIn()}, term * (rp+1), rp);
                setTimeout(function () {$('#imgFind').fadeOut()}, term * (rp+10), rp);

            }
        }
        pathStart = pathEnd;
    })

    function init() {
        currentPath = [];
        pathStart = [0, 0];
        pathEnd = [0, 0];
        redraw();
    }
});
