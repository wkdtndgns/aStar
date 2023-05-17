/**
 * test2
 *
 * @author shjang02 < shjang02@simplexi.com >
 * @since 2023. 05. 17.
 */
// A $( document ).ready() block.
$(document).ready(function () {
    // 장애물 랜덤 생성
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
        redraw();
    });

    // 장애물 초기화
    $('#btnResetWall').click(function () {
        for (var x = 0; x < worldWidth; x++) {
            world[x] = [];
            for (var y = 0; y < worldHeight; y++) {
                world[x][y] = 0;
            }
        }
        redraw();
    });
});
