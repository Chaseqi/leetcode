package questions.leetcode.questions;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.TreeMap;

public class RandomSelectFromRectangle {

	Random rand = new Random();
	Point selectRandomPointFromOneRectangle(Rectangle rec) {
		return new Point(rec.x1 + rand.nextInt(rec.x2 - rec.x1 + 1), rec.y1 + rand.nextInt(rec.y2 - rec.y1 + 1));
	}
	
	Point selectRandomPointFromMultipleNonOverlappingRectangles(List<Rectangle> recs) {
		Rectangle result = null;
		int totalArea = 0;
		
		for (Rectangle rec : recs) {
			int area = getArea(rec);
			if (rand.nextInt(totalArea + area) >= totalArea) {
				result = rec;
			}
			
			totalArea += area;
		}
		
		return selectRandomPointFromOneRectangle(result);
	}
	
	// the bottom line of all the rectangles are on the x-axis 
	Point selectRandomPointFromMultipleOverlappingRectangles(List<Rectangle> recs) {
		List<Edge> edges = new ArrayList<>();
		
		for (Rectangle rec : recs) {
			edges.add(new Edge(rec.x1, rec.y1, true));
			edges.add(new Edge(rec.x2, rec.y1, false));
		}
		
		Collections.sort(edges, (edge1, edge2) -> {
			int diff = edge1.x - edge2.x;
			
			if (diff != 0) {
				return diff;
			}
			
			if (edge1.start && edge2.start) {
				if (edge1.h > edge2.h) {
					return -1;
				}
				
				return 1;
			}
			
			if (!edge2.start && !edge2.start) {
				if (edge1.h > edge2.h) {
					return 1;
				}
				
				return -1;
			}
			
			if (edge1.start) {
				return -1;
			}
			
			return 1;
		});
		
		TreeMap<Integer, Integer> map = new TreeMap<>((i1, i2) -> i2 - i1);
		List<Rectangle> nonOverlappingRecs = new ArrayList<>();
		
		Rectangle r = null;
		for (Edge edge : edges) {
			if (edge.start) {
				if (map.isEmpty() || map.firstKey() < edge.h) {
					r = new Rectangle();
					r.x1 = edge.x;
					r.y1 = edge.h;
					map.put(edge.h, 1);
				} else {
					Integer count = map.get(edge.h);
					if (count == null) {
						map.put(edge.h, 1);
					} else {
						map.put(edge.h, count + 1);
					}
				}
			} else {
				Integer count = map.get(edge.h);
				
				if (count > 1) {
					map.put(edge.h, count - 1);
				} 
				
				map.remove(edge.h);
				
				if (map.isEmpty() || map.firstKey() < edge.h) {
					r.x2 = edge.x;
					nonOverlappingRecs.add(r);
				}
			}
		}
		
		return selectRandomPointFromMultipleNonOverlappingRectangles(nonOverlappingRecs);
	}
	
	private int getArea(Rectangle rec) {
		return (rec.x2 - rec.x1 + 1) * (rec.y2 - rec.y1 + 1);
	}
	
	class Rectangle {
		int x1, y1, x2, y2;
	}
	
	class Point {
		int x, y;
		
		Point(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
	
	class Edge {
		public int x, h;
		boolean start;
		
		Edge(int x, int h, boolean start) {
			this.x = x;
			this.h = h;
			this.start = start;
		}
	}
}


