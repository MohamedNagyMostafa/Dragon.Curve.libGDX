
// package name.

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;

import java.util.ArrayList;


public class DragonCurve extends ApplicationAdapter {



    private ShapeRenderer shapeRenderer;

    @Override
    public void create () {
        shapeRenderer = new ShapeRenderer();
    }

    @Override
    public void render () {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        shapeRenderer.begin(ShapeRenderer.ShapeType.Line);
        ArrayList<Vector2> points = new ArrayList<Vector2>();
        /// initial line.
        points.add(new Vector2(Gdx.graphics.getWidth()/2, Gdx.graphics.getHeight() / 2));
        points.add(new Vector2(Gdx.graphics.getWidth()/2 + 10, Gdx.graphics.getHeight()/ 2));
        
        // Set Dragon curve order.
        points = dragonCurveDrawing(10, points);

        float[] pointArr = new float[points.size() * 2];

        for(int i = 0, j = 0; i < points.size() ; i++){
            pointArr[j++] = points.get(i).x;
            pointArr[j++] = points.get(i).y;
        }

        shapeRenderer.setColor(Color.GREEN);

        shapeRenderer.polyline(pointArr);

        shapeRenderer.end();
    }

    ArrayList<Vector2> dragonCurveDrawing(int order, ArrayList<Vector2> points){
        if(order > 0){
            ArrayList<Vector2> newPoints = generateNewPoints(points, points.size());
            dragonCurveDrawing(--order, newPoints);
        }
        return points;
    }

    ArrayList<Vector2> generateNewPoints(ArrayList<Vector2> points, int iteration){
        if(-- iteration > 0){

            if(points.get(iteration).x == points.get(iteration - 1).x)
                points.add(
                        new Vector2(
                                points.get(points.size()-1).x - (points.get(iteration).y - points.get(iteration - 1).y),
                                points.get(points.size() - 1).y
                        ));
            else
                points.add(
                        new Vector2(
                                points.get(points.size()-1).x ,
                                points.get(points.size() - 1).y + (points.get(iteration).x - points.get(iteration - 1).x)
                        ));

            generateNewPoints(points, iteration);
        }

        return points;
    }
}
